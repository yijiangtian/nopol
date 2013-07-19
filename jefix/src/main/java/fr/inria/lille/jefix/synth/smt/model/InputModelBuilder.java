/*
 * Copyright (C) 2013 INRIA
 *
 * This software is governed by the CeCILL-C License under French law and
 * abiding by the rules of distribution of free software. You can use, modify
 * and/or redistribute the software under the terms of the CeCILL-C license as
 * circulated by CEA, CNRS and INRIA at http://www.cecill.info.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the CeCILL-C License for more details.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C license and that you accept its terms.
 */
package fr.inria.lille.jefix.synth.smt.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import fr.inria.lille.jefix.patch.Level;
import fr.inria.lille.jefix.synth.InputOutputValues;

/**
 * @author Favio D. DeMarco
 * 
 */
public final class InputModelBuilder {

	private final ValuesModel data;

	private static final List<Object> CONSTANTS = ImmutableList.<Object> of(-1, 0, 1, true, false);

	public InputModelBuilder(final InputOutputValues data) {
		this.data = new ValuesModel(data, CONSTANTS);
	}

	public InputModel buildFor(final Level level) {

		InputModel model = this.createModel();

		switch (level) {

		case MULTIPLICATION:

		case ITE_ARRAY_ACCESS:

		case ARITHMETIC:

		case LOGIC:

		case COMPARISON:

		case CONSTANTS:
			new ConstantsModelBuilder(this.data).addTo(model);
			break;
		default:
			throw new IllegalStateException("Unknown level: " + level);
		}

		return model;
	}

	/**
	 * @param outputType
	 * @return
	 */
	private InputModel createModel() {

		// XXX FIXME TODO Law of Demeter
		Type outputType = Type.ValueToType.INSTANCE.apply(this.data.getOutputValues().iterator().next());

		List<Type> inputTypes = new ArrayList<>();
		for (Collection<Object> values : this.data.getInputvalues().asMap().values()) {
			inputTypes.add(Type.ValueToType.INSTANCE.apply(values.iterator().next()));
		}

		return new InputModel(inputTypes, new ArrayList<Component>(), outputType);
	}
}